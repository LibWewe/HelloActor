package cn.ww.actor

import akka.actor.{Actor, ActorRef}

class LongGeActor(val fg: ActorRef) extends Actor {
    override def receive: Receive = {
        case "start" => {
            println("龙哥：I am OK")
            Thread.sleep(1000)
            fg ! "啪"
        }
        case "啪啪" => {
            println("你真猛")
            Thread.sleep(1000)
            fg ! "啪"
        }
    }
}
