KL_TOKEN="Bearer <ACCESS_TOKEN>"
OPENSHIFT_DOMAIN=ibuziuk-che.8a09.starter-us-east-2.openshiftapps.com

URL=http://hello-openshift-route-${OPENSHIFT_DOMAIN}/
while true; do
    curl -s -o /dev/null -w "%{http_code}" ${URL}
    echo
done