(defproject    superstructor/re-highlight "lein-git-inject/version"
  :description "Reagent wrapper for Highlight.js"
  :url         "https://github.com/superstruct/re-highlight.git"
  :license     {:name "MIT"}

  :dependencies [[org.clojure/clojure       "1.10.3"   :scope "provided"]
                 [org.clojure/clojurescript "1.10.773" :scope "provided"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library
                               org.clojure/google-closure-library-third-party]]
                 [thheller/shadow-cljs      "2.11.24"  :scope "provided"]
                 [reagent                   "1.0.0"    :scope "provided"]
                 [re-com                    "2.13.2"   :scope "provided"]]

  :plugins [[day8/lein-git-inject "0.0.14"]
            [lein-shadow          "0.3.1"]
            [lein-ancient         "0.6.15"]
            [lein-shell           "0.5.0"]
            [lein-pprint          "1.3.2"]]

  :middleware [leiningen.git-inject/middleware]

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.2"]]}}

  :source-paths ["src"]
  :test-paths ["test"]
  :resource-paths ["resources"]

  :clean-targets ^{:protect false} [:target-path
                                    "shadow-cljs.edn"
                                    "node_modules"
                                    "resources/public/scripts/compiled"]

  :deploy-repositories [["clojars" {:sign-releases false
                                    :url "https://clojars.org/repo"
                                    :username :env/CLOJARS_USERNAME
                                    :password :env/CLOJARS_TOKEN}]]

  :shadow-cljs {:builds {:demo {:target           :browser
                                :modules          {:demo {:init-fn re-highlight-demo.core/mount-root}}
                                :compiler-options {:closure-defines {re-highlight-demo.config/version "lein-git-inject/version"}}
                                :dev              {:asset-path       "/scripts/compiled/dev"
                                                   :output-dir       "resources/public/scripts/compiled/dev"
                                                   :compiler-options {:external-config {:devtools/config {:features-to-install [:formatters :hints]}}}}
                                :release          {:output-dir "resources/public/scripts/compiled/prod"}
                                :devtools         {:http-port        9000
                                                   :http-root        "resources/public"
                                                   :push-state/index "index_dev.html"}}}}

  :release-tasks [["deploy" "clojars"]]

  :aliases {"watch" ["with-profile" "dev" "do"
                     ["clean"]
                     ["shadow" "watch" "demo"]]})
