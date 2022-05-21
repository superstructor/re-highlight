(ns re-highlight.core
  (:require
    [goog.object                          :as gobj]
    [reagent.core                         :as r]
    [reagent.dom                          :as rdom]
    ["highlight.js/lib/core"              :as hljs]
    ["highlight.js/lib/languages/clojure" :as clojure]))

(defn register-language
  [label js-obj]
  (.registerLanguage hljs label js-obj))

(register-language "clojure" clojure)

(def highlight-element (gobj/get hljs "highlightElement"))

(defn did-mount
  [this]
  (when-let [el (gobj/get (rdom/dom-node this) "firstChild")]
    (highlight-element el)))

(defn did-update
  [this old-argv old-state snapshot]
  (when-let [el (gobj/get (rdom/dom-node this) "firstChild")]
    (-> (gobj/get hljs "initHighlighting")
        (gobj/set "called" false))
    (highlight-element el)))

(defn render
  [{:keys [class style language]} & children]
  [:pre
   {:class class
    :style style}
   (into
     [:code
      {:class language}]
     children)])

(defn highlight
  []
  (r/create-class
    {:component-did-mount  did-mount
     :component-did-update did-update
     :reagent-render       render}))