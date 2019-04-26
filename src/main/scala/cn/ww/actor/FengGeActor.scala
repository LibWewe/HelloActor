package cn.ww.actor

import akka.actor.Actor

class FengGeActor extends Actor {
    override def receive: Receive = {
        case "start" => println("峰哥：I am OK")
        case "啪" => {
            println("那必须滴")
            Thread.sleep(1000)
            sender() ! "啪啪"
        }
    }
}
