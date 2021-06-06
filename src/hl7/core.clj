(ns hl7.core)

(defn hl7v2-to-fhirjson
  "given an hl7v2 message string (in hat and bar notation) convert it to FHIR json"
  [hl7-message]
  (let [converter (new io.github.linuxforhealth.hl7.HL7ToFHIRConverter)
        options (-> (new io.github.linuxforhealth.hl7.ConverterOptions$Builder)
                    .withValidateResource
                    .withPrettyPrint
                    .build)
        json-fhir-message (.convert 
                           converter 
                           hl7-message
                           ; options
                           )] 
    json-fhir-message))
; if you un comment options something tries to instantiate this:
; ca.uhn.fhir.util.VersionIndependentConcept
; but only this is on the classpath:
; ca.uhn.fhir.util.FhirVersionIndependentConcept


(defn fhirjson-to-fhirrdf
  "given a FHIR json message string convert it to FHIR RDF"
  [fhirjson-message]
  (let [ctx (ca.uhn.fhir.context.FhirContext/forR4 )
        parser (.newJsonParser ctx)
        parsed (.parseResource parser fhirjson-message)
        rdf (.encodeResourceToString (.newRDFParser ctx) ; there is also newXMLParser, etc.
                                     parsed)]
    rdf))

