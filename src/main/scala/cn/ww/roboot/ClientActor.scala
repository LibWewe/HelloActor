package cn.ww.roboot

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn
import scala.util.control.Breaks

class ClientActor(serverHost: String, serverPort: Int, val clientId: String) extends Actor {
    var serverActorRef: ActorSelection = _

    override def preStart(): Unit = {
        serverActorRef = context.actorSelection(s"akka.tcp://Server@${serverHost}:${serverPort}/user/ServerActorRef/")
    }

    override def receive: Receive = {
        case "start" => println(s"Client $clientId started")
        case question: String => {
            serverActorRef ! Message(question)
        }
        case Message(msg) => println(s"收到服务端消息:$msg")
        case _ => println("What is this")
    }
}

object ClientActor {
    def main(args: Array[String]): Unit = {
        val host = "127.0.0.1"
        val port = 8809

        val serverHost = "127.0.0.1"
        val serverPort = 8808


        val config = ConfigFactory.parseString(
            s"""
               |akka.actor.provider="akka.remote.RemoteActorRefProvider"
               |akka.remote.netty.tcp.hostname=$host
               |akka.remote.netty.tcp.port=$port
             """.stripMargin
        )
        val clientActorSystem = ActorSystem("client", config)

        val nmwActorRef = clientActorSystem.actorOf(Props(new ClientActor(serverHost, serverPort.toInt, "NMW001")), "NMW001")

        nmwActorRef ! "start"

        while (true) {
            val question = StdIn.readLine()
            nmwActorRef ! question
        }
    }
}
