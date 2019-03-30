set -e
cd "$(dirname "$0")"
mkdir -p bin
find . -name *.java | javac -d bin -classpath "lib/antlr-runtime-4.7.2.jar" @/dev/stdin
