KL_TOKEN="Bearer <ACCESS_TOKEN>"
OPENSHIFT_DOMAIN=osio-ci-ee3-preview.b542.starter-us-east-2a.openshiftapps.com

URL=http://hello-openshift-route-${OPENSHIFT_DOMAIN}/
while true; do
    curl -s -o /dev/null -w "%{http_code}" ${URL}
    echo
done
