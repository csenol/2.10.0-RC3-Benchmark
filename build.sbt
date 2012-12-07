import AssemblyKeys._ 

assemblySettings

version := "1.0.0"

scalaVersion := "2.10.0-RC3"

scalacOptions ++= Seq("-deprecation")

jarName in assembly := "bench2.10.jar"

