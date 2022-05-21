## Unreleased

## 2.0.0 (2022-05-22)

#### Changed

- Upgrade Highlight.js to [11.5.1](https://github.com/highlightjs/highlight.js/blob/main/CHANGES.md#version-1150)
- BREAKING: Only include `"hightlight.js/lib/core"` to fix build performance issues
  caused by previously loading all languages. Now only Clojure support is
  registered by default. If you are using any other language, you now need to
  explicitly require and register it! E.g.
  ```clojure
  (require ["highlight.js/lib/languages/rust" :as rust])
  (re-highlight/register-language "rust" rust)
  ```

## 1.1.0 (2021-07-14)

#### Changed

- Upgrade highlight.js to 11.1.0

## 1.0.0 (2021-06-23)

#### Changed

- Upgrade highlight.js to 11.0.1

## 0.0.2 (2021-04-06)

#### Changed

- Upgrade highlight.js to 10.7.2

## 0.0.1 (2021-03-23)

Initial release.
