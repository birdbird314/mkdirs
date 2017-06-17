(ns mkdirs.core
  (:require [clojure.string :as str]))

(defn- root? [dir] (= "/" dir))

(defn- dir-exists-in? 
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

(defn mkdirs-single 
  [existing to-add]
  (loop [result 0
         dir to-add]
      (if (dir-exists-in? existing dir)
        result 
        (recur (inc result) (parent dir)))))

(defn mkdirs 
  [existing dirs-to-add] 
  (reduce 
    #(+ %1 (mkdirs-single existing %2))
	0
	dirs-to-add))
