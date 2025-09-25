#!/usr/bin/env zsh

# (#)update-person.sh 0.1.0   09/25/2025
#
# @author   Jonathan Parker
# @version  0.1.0
# @since    0.1.0
#
# MIT License
#
# Copyright (c) 2025 Jonathan M. Parker
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

SITE="http://localhost:8080"
PERSON_ID="4"

curl -X PUT ${SITE}/api/person/${PERSON_ID} \
  -H "Content-Type: application/json" \
  -d '{
    "lastName": "Uhura",
    "firstName": "Nyota",
    "phoneNumber": "555-999-8888",
    "emailAddress": "nyota.uhura@domain.com"
  }'
