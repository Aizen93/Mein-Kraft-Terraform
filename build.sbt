ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.mein.kraft"
ThisBuild / organizationName := "Mein Kraft"

val libgdxVersion = "1.9.10"

lazy val commonSettings = Seq()

lazy val core = project
  .settings(
    libraryDependencies ++= Seq(
      "com.badlogicgames.gdx" % "gdx" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-bullet" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-tools" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-box2d" % libgdxVersion
    )
  )

lazy val desktop = project
  .dependsOn(core)
  .settings(
    libraryDependencies ++= Seq(
      "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % libgdxVersion,
      "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion classifier "natives-desktop",
      "com.badlogicgames.gdx" % "gdx-bullet-platform" % libgdxVersion classifier "natives-desktop"
    ),
    Compile / fork := true,
    Compile / run / baseDirectory := file("core/src/main/scala/assets")
  )
