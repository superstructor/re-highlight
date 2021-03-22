(ns re-highlight-demo.core
  (:require
    [reagent.dom :as rdom]
    [re-com.core :as rc :refer [at]]
    [re-highlight.core :refer [highlight]]))

(defn main
  []
  [rc/v-box
   :children [[rc/title
               :level :level1
               :label "Reagent Highlight.js Wrapper"]
              [highlight
               {:language "clojure"}
               "(defn fibo-recursive [n]\n         (if (or (= n 0) (= n 1))\n           n\n           (+ (fibo-recursive (- n 1)) (fibo-recursive (- n 2)))))"]]])


(defn get-element-by-id
  [id]
  (.getElementById js/document id))

(defn ^:dev/after-load mount-root
  []
  (rdom/render [main] (get-element-by-id "app")))