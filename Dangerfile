android_lint.gradle_task = "lintDebug"
android_lint.report_file = "androidApp/build/reports/lint-results-debug.xml"
android_lint.filtering = true
android_lint.lint
kotlin_detekt.skip_gradle_task = true
kotlin_detekt.filtering = true
Dir["**/build/reports/detekt/detekt.xml"].each do |file|
  kotlin_detekt.report_file = file
  kotlin_detekt.detekt
end
