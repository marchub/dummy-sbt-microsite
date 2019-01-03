import microsites.ExtraMdFileConfig

name := "dummy"
version := "1.0"
scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.10.7", "2.11.12")

micrositeName := "Microsite Demo"
micrositeDescription := "How to use the sbt-microsites plugin"
micrositeHighlightTheme := "atom-one-light"
micrositeDocumentationUrl := "/dummy-sbt-microsite/docs/"
micrositeDataDirectory := (resourceDirectory in Compile).value / "microsite" / "data"
micrositeBaseUrl := "dummy-sbt-microsite"

micrositeGithubOwner := "47deg"
micrositeGithubRepo := "dummy-sbt-microsite"

micrositeExtraMdFiles := Map(
  file("CONSEQUAT.md") -> ExtraMdFileConfig(
    "consequat.md",
    "page",
    Map("title" -> "Consequat", "section" -> "consequat", "position" -> "0")
  ))
micrositePalette := Map(
  "brand-primary"     -> "#FC4053",
  "brand-secondary"   -> "#B92239",
  "brand-tertiary"    -> "#8C192F",
  "gray-dark"         -> "#464646",
  "gray"              -> "#7E7E7E",
  "gray-light"        -> "#E8E8E8",
  "gray-lighter"      -> "#F6F6F6",
  "white-color"       -> "#FFFFFF")

autoAPIMappings := true

//com.typesafe.sbt.SbtGhPages.GhPagesKeys.ghpagesNoJekyll := false

enablePlugins(MicrositesPlugin)

val sc = "org.scalacheck" % "scalacheck" % "1.14.0" withSources()
