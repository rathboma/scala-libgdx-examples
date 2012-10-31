package com.rathboma.playpen


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

object Main {
  def main(arg: Array[String]) = {
    val cfg = new LwjglApplicationConfiguration()
    cfg.title = "puzzleplatform"
    cfg.useGL20 = true
    cfg.width = 800
    cfg.height = 480
    cfg.resizable = false
    
    new LwjglApplication(new PlaypenGame(), cfg)
  }
}
