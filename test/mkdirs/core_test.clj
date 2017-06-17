(ns mkdirs.core-test
  (:require [clojure.test :refer :all]
            [mkdirs.core :refer :all]))

(deftest mkdirs-test
  (testing "Should return 0 for root dir"
    (is (zero? (mkdirs `() '("/")))))
    
  (testing "Should return path depth when there are no predefined dirs "
    (is (= 2 (mkdirs `() '("/a/a")))))
  
  (testing "Should return 0 when directory already exist"
    (is (zero? (mkdirs `("/a/a") '("/a"))))
    (is (zero? (mkdirs `("/a/a") '("/")))))

  (testing "Should count number of mkdirs for one dirs"
    (is (= 2 (mkdirs '("/a") '("/a/b/c"))))) 
 
  (testing "Should count number of mkdirs for multiple dirs"
    (is (= 4 (mkdirs `("/a" "/b/c") '("/a/b" "/a/c" "/b/c/d/e"))))))
