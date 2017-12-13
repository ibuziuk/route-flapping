KL_TOKEN=<TOKEN>
OPENSHIFT_DOMAIN=ibuziuk-che.8a09.starter-us-east-2.openshiftapps.com

URL=https://hello-openshift-route-${OPENSHIFT_DOMAIN}/
while true; do
    curl --header "authorization: Bearer ${KL_TOKEN}" \
       -s -o /dev/null -w "%{http_code}" ${URL}
    echo
done