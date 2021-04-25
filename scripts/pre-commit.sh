
echo "Running spotless check"
./gradlew spotlessApply --daemon

status=$?

# return 1 exit code if running checks fails
[ $status -ne 0 ] && exit 1
exit 0