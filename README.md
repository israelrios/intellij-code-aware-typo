# Code-Aware Typo

**Disables typo inspection on specific code elements in IntelliJ IDEA.**

This plugin suppresses the "Typo" inspection for:

* Keys in `.properties` files.
* Names of Java variables, methods, and classes.
* Names in HTML and XML.
* Names in JavaScript, CSS, SQL, and Shell Script.
* Resolvable string literal references inside Java annotation attributes

This prevents the IDE from flagging these elements as spelling errors, even if they don't conform to standard dictionary
words.

**Dependencies:**

* IntelliJ IDEA

**License:** GNU Affero General Public License v3 (AGPLv3)

**Installation:** Install directly from the JetBrains Marketplace (search for "Code-Aware Typo").

**How it works:**

The plugin uses the `InspectionSuppressor` (for Properties files) and `SpellcheckingStrategy` (for other languages)
extension points to override the default spellchecking behavior. It specifically targets `PsiNamedElement` instances (
variables, methods, classes) and, in the case of annotations, `PsiLiteralExpression` elements with resolvable
references.

**Building from source:**

This project uses Gradle.

1. Clone the repository.
2. Run `./gradlew buildPlugin` to build the plugin.
3. The plugin ZIP file will be in `build/distributions`.

**Contribution:** See AGPLv3 License (included) for details.