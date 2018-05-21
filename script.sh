#!/bin/bash
awk -F, '{printf("%s%s", $1, ";")}' $1 | sed 's/.$//' > tmp && mv tmp $1