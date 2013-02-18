
package com.rathboma.playpen.util
import com.badlogic.gdx.utils.Logger

trait Logging {
  val logger = new Logger(getClass.getName, Logger.DEBUG)
}