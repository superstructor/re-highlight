(ns re-highlight.core-test
  (:require
    [clojure.test :refer [deftest is testing async use-fixtures]]
    [re-highlight.core :refer [highlight]]))

(deftest returns-component
  (is (instance? js/Function (highlight))))
