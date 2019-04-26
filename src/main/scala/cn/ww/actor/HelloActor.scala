package cn.ww.actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class HelloActor extends Actor {
    override def receive: Receive = {
        case "你好帅" => println("尽说实话，我喜欢你这种人")
        case "丑" => println("滚犊子")
        case _ => println("你说啥，听不懂")
    }
}

object HelloActor {
    private val nbFactory: ActorSystem = ActorSystem("NbFactory")
    private val helloActorRef: ActorRef = nbFactory.actorOf(Props[HelloActor], "HelloActorRef")

    def main(args: Array[String]): Unit = {
        helloActorRef ! "你好帅"
        helloActorRef ! "123"
    }
}
