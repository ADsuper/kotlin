// WITH_RUNTIME

fun <T> foo(l: () -> T): T = l()

fun testSimple(): Int {
    return <error descr="[TYPE_MISMATCH] Type mismatch: inferred type is Any? but Int was expected">foo {
        <error descr="[CONTRADICTION_IN_CONSTRAINT_SYSTEM] Contradictory requirements for type variable 'T':
should be a subtype of: Int (expected type for 'foo')
should be a supertype of: String">"abc"</error>
    }</error>
}

fun <T> subCall(x: T): T = x

fun testSubCall(): Int {
    return <error descr="[TYPE_MISMATCH] Type mismatch: inferred type is Any? but Int was expected">foo {
        <error descr="[CONTRADICTION_IN_CONSTRAINT_SYSTEM] Contradictory requirements for type variable 'T':
should be a subtype of: TypeVariable(T), Int
should be a supertype of: String (for parameter 'x')">subCall("abc")</error>
    }</error>
}

fun testSpecialCall(): Int {
    return <error descr="[TYPE_MISMATCH] Type mismatch: inferred type is Any? but Int was expected">foo {
        <error descr="[CONTRADICTION_FOR_SPECIAL_CALL] Result type for 'if' expression cannot be inferred:
should be conformed to: TypeVariable(T), Int
should be a supertype of: {Int & Byte & Short & Long} (for parameter 'thenBranch'), String (for parameter 'elseBranch')">if (true) 123 else "abc"</error>
    }</error>
}
