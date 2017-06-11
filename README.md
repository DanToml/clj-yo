# clj-yo

A very simple clojure library to send and receive [yo's](https://justyo.co).

## Usage

(ns my-app.core
  (:require [clj-yo.core :as yo]))

(defn do-the-thing
  []
  (yo/yo-user "some-yo-username"))

## License

Copyright © 2017 Danielle Tomlinson

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
