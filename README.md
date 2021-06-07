# hl7


 cat ./sample_unix.hl70 | curl --data-binary @- -v 'http://HOST:8080/function/hl7-to-fhir-json
