name := "dummy"
version := "1.0"
scalaVersion := "2.11.8"

micrositeName := "Typelevel"
micrositeDescription := "Cats"
micrositeHighlightTheme := "atom-one-light"
micrositeDocumentationUrl := "docs.html"
micrositeBaseUrl := "cattts"

includeFilter in makeSite := "*.html" & "*.swf" & "*.yml"
micrositeExtratMdFiles := Map(file("CONTRIBUTING.md") -> "contributing.md")
micrositePalette := Map(
  "brand-primary"     -> "#FC4053",
  "brand-secondary"   -> "#B92239",
  "brand-tertiary"    -> "#8C192F",
  "gray-dark"         -> "#464646",
  "gray"              -> "#7E7E7E",
  "gray-light"        -> "#E8E8E8",
  "gray-lighter"      -> "#F6F6F6",
  "white-color"       -> "#FFFFFF")

enablePlugins(MicrositesPlugin)