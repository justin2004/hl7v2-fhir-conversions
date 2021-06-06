(ns hl7.core-test
  (:require [clojure.test :refer :all]
            [hl7.core :refer :all]))

; TODO need to validate json or something
(deftest b-test
  (testing "hl7v2 to fhir json"
    (is (hl7v2-to-fhirjson (slurp "./sample_unix.hl7")))))

; TODO need to count triples or something
(deftest c-test
  (testing "fhir json to fhir rdf"
    (is (fhirjson-to-fhirrdf (hl7v2-to-fhirjson (slurp "./sample_unix.hl7"))))))

; what is the test's PWD?
; (hl7v2-to-fhirjson (slurp "./sample_unix.hl7"))
