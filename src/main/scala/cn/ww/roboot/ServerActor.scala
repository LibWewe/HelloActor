package cn.ww.roboot

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class ServerActor extends Actor {
    override def receive: Receive = {
        case "start" => {
            println("Server started")
        }
        case Message(msg) => {
            println(s"收到客户端消息：$msg")
            msg match {
                case "你叫什么名字" => sender() ! Message("我是铁扇公主")
                case "你是男是女" => sender() ! Message("老娘是男的")
                case "你有男票吗" => sender() ! Message("没有")
                case _ => sender() ! Message("What you say ?")
            }
        }
        case _ => println("这是啥")
    }
}

object ServerActor {
    def main(args: Array[String]): Unit = {
        val host = "127.0.0.1"
        val port = 8808
        val config = ConfigFactory.parseString(
            s"""
               |akka.actor.provider="akka.remote.RemoteActorRefProvider"
               |akka.remote.netty.tcp.hostname=$host
               |akka.remote.netty.tcp.port=$port
            """.stripMargin
        )
        val serverActorSystem = ActorSystem("Server", config)
        val serverActorRef = serverActorSystem.actorOf(Props[ServerActor], "ServerActorRef")
        serverActorRef ! "start"
    }
}
