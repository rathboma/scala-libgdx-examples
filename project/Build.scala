import sbt._

import Keys._
import AndroidKeys._
import sbtassembly.Plugin._
import AssemblyKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "Playpen",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.8.2",
    platformName in Android := "android-10"
  )

  val proguardSettings = Seq (
    useProguard in Android := true
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "playpen",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.8.RC1" % "test"
    )
}

object AndroidBuild extends Build {
  lazy val core = Project (
    "core",
    file("core")
  )

  // lazy val android = Project (
  //   "android",
  //   file("android"),
  //   settings = General.fullAndroidSettings
  //   ) dependsOn core

  lazy val desktop = Project (
    "desktop",
    file("desktop"),
    settings = Defaults.defaultSettings ++ assemblySettings ++ Seq (
      mainClass in assembly := Some("com.rathboma.playpen.Main")
      )
    ) dependsOn core

  // lazy val tests = Project (
  //   "tests",
  //   file("tests"),
  //   settings = General.settings ++
  //              AndroidTest.androidSettings ++
  //              General.proguardSettings ++ Seq (
  //     name := "Sheep HerderTests"
  //   )
  // ) dependsOn core
}
