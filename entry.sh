#!/bin/bash

#cat ./sample_unix.hl7 | lein run -m hl7.core/hl7v2-file-to-fhirjson /dev/stdin > /tmp/out && cat /tmp/output.json
# cat ./sample_unix.hl7 | lein run -m hl7.core/hl7v2-file-to-fhirjson /dev/stdin ; cat /tmp/output.json
lein run -m hl7.core/hl7v2-file-to-fhirjson /dev/stdin ; cat /tmp/output.json
