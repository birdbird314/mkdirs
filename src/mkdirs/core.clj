(ns mkdirs.core
  (:require [clojure.string :as str]))

(defn- root? [dir] (= "/" dir))

(defn- dir-exists? 
  [existing to-add]
  (or
    (root? to-add)
    (some #(str/starts-with? % to-add) existing)))

(defn- parent 
  [dir] 
  (let [result 
         (reduce
           #(str %1 "/" %2) 
           (drop-last (str/split dir #"/")))]
    (if (empty? result) 
	  "/"
	  result)))

(defn mkdirs 
  [existing to-add]
  (loop [result 0
         dir (first to-add)]
    (if (dir-exists? existing dir)
      result 
      (recur (inc result) (parent dir)))))
