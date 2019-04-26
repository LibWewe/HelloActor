package cn.ww.actor

import akka.actor.{ActorRef, ActorSystem, Props}

object PingPong extends App {
    private val pingPongActorSystem = ActorSystem("PingPong")
    private val fengGeRef: ActorRef = pingPongActorSystem.actorOf(Props[FengGeActor], "FengGeRef")
    private val longGeRef: ActorRef = pingPongActorSystem.actorOf(Props(new LongGeActor(fengGeRef)), "LongGeRef")

    fengGeRef ! "start"
    longGeRef ! "start"
}
