set -e
cd "$(dirname "$0")"
export CCHK="java -classpath ./lib/antlr-runtime-4.7.2.jar:./bin Mx_compiler.compiler.Main"
$CCHK
