(defproject hl7 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/java.classpath "1.0.0"]]
  :resource-paths ["built-for-hl7-stuff-shared-things-1.0.0-jar-with-dependencies.jar" "."]
  :repl-options {:init-ns hl7.core})
