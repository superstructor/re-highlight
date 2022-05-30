(ns re-highlight.core
  (:require
    [goog.object                          :as gobj]
    [reagent.core                         :as r]
    [reagent.dom                          :as rdom]
    ["highlight.js/lib/core"              :as hljs]
    ["highlight.js/lib/languages/clojure" :as clojure]))

(def hljs-highlight-element (gobj/get hljs "highlightElement"))
(def ^:private hljs-init-highlighting (gobj/get hljs "initHighlighting"))
(def hljs-register-language (gobj/get hljs "registerLanguage"))

(defn hljs-compatible?
  "Return true if the Highlight.js library exposes the API we expect to see in
   a compatible version, otherwise false. If this returns false, it is likely
   that your transitive dependencies are pulling in a different version of
   Highlight.js to the version that re-highlight depends on."
  []
  (and
    (fn? hljs-highlight-element)
    (fn? hljs-init-highlighting)
    (fn? hljs-register-language)))

(defn register-language
  [label js-obj]
  (when (fn? hljs-register-language)
    (hljs-register-language label js-obj)))

(register-language "clojure" clojure)

(defn did-mount
  [this]
  (when-let [el (gobj/get (rdom/dom-node this) "firstChild")]
    (hljs-highlight-element el)))

(defn did-update
  [this old-argv old-state snapshot]
  (when-let [el (gobj/get (rdom/dom-node this) "firstChild")]
    (gobj/set hljs-init-highlighting "called" false)
    (hljs-highlight-element el)))

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