package com.mein.kraft.core.Command

import scala.collection.mutable.Queue

object CommandInvoker {

  var commandBuffer: Queue[ICommand] = Queue[ICommand]()

  def addCommand(command: ICommand): Unit = {
    commandBuffer.enqueue(command)
  }

  def unstack(): Unit = {
    if(commandBuffer.count(_ => true) > 0){
      commandBuffer.dequeue().execute()
    }
  }
}
