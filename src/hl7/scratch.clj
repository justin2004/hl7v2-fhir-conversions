(ns hl7.scratch)

;;;;;;;;;;;;;;
(.println java.lang.System/out "hello")


(require '[clojure.java.classpath :as cp])
(require '[clojure.java.io :as io])
(print (cp/classpath ))
org.apache.commons.configuration2.PropertiesConfiguration

(def converter (new io.github.linuxforhealth.hl7.HL7ToFHIRConverter))

(def options (-> (new io.github.linuxforhealth.hl7.ConverterOptions$Builder)
                   .withValidateResource
                   .withPrettyPrint
                   .build))

(require '[clojure.string :as str])
(def hl7-message (str
 "MSH|^~\\&|SE050|050|PACS|050|20120912011230||ADT^A01|102|T|2.6|||AL|NE|764|ASCII||||||^4086::132:2A57:3C28^IPv6\r"
 "EVN||201209122222\r"
 "PID|0010||PID1234^5^M11^A^MR^HOSP~1234568965^^^USA^SS||DOE^JOHN^A^||19800202|F||W|111 TEST_STREET_NAME^^TEST_CITY^NY^111-1111^USA||(905)111-1111|||S|ZZ|12^^^124|34-13-312||||TEST_BIRTH_PLACE\r"
 "PV1|1|ff|yyy|EL|ABC||200^ATTEND_DOC_FAMILY_TEST^ATTEND_DOC_GIVEN_TEST|201^REFER_DOC_FAMILY_TEST^REFER_DOC_GIVEN_TEST|202^CONSULTING_DOC_FAMILY_TEST^CONSULTING_DOC_GIVEN_TEST|MED|||||B6|E|272^ADMITTING_DOC_FAMILY_TEST^ADMITTING_DOC_GIVEN_TEST||48390|||||||||||||||||||||||||201409122200|20150206031726\r"
 "OBX|1|TX|1234||ECHOCARDIOGRAPHIC REPORT||||||F|||||2740^TRDSE^Janetary~2913^MRTTE^Darren^F~3065^MGHOBT^Paul^J~4723^LOTHDEW^Robert^L|\r"
 "AL1|1|DRUG|00000741^OXYCODONE||HYPOTENSION\r"
 "AL1|2|DRUG|00001433^TRAMADOL||SEIZURES~VOMITING\r"
 "PRB|AD|200603150625|aortic stenosis|53692||2||200603150625"))


(print hl7-message)
(def hl7-message (slurp "./sample_unix.hl7"))
(def hl7-message (slurp "./sample_win.hl7"))
(def json-fhir-message nil)
(def json-fhir-message (.convert 
                        converter 
                        hl7-message
                        ; options
                        ))
(print json-fhir-message)
; if you un comment options something tries to instantiate this:
ca.uhn.fhir.util.VersionIndependentConcept
; but only this is on the classpath:
ca.uhn.fhir.util.FhirVersionIndependentConcept


;   now json to rdf
;;;;;;;;;;
(setf *ctx* (jstatic "forR4" "ca.uhn.fhir.context.FhirContext"))
(def ctx (ca.uhn.fhir.context.FhirContext/forR4 ))
(setf parser (#"newJsonParser" *ctx*))
(def parser (.newJsonParser ctx))
(setf *parsed* (#"parseResource" parser *m*))
(def parsed (.parseResource parser json-fhir-message))
(setf rdf (#"encodeResourceToString" (#"newRDFParser" *ctx*)
(def rdf (.encodeResourceToString (.newRDFParser ctx)
                                  parsed))
(print rdf)
