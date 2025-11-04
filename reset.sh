#!/bin/bash
find . -mindepth 1 ! -regex '^./\.git\(/.*\)?' -exec rm -rf {} +
echo "# Empty repo" > README.md
git add -A
git commit -m "Reset repo"
git push
