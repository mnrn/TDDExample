package money

open class Money(private val amount: Int, private val currency: String): Expression {

    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, currency)
    }

    override fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)
    }

    fun currency(): String = currency
    fun amount(): Int = amount

    override fun equals(other: Any?): Boolean {
        val money = checkNotNull(other){"other must be not null."} as Money
        return amount() == money.amount() && currency() == money.currency()
    }

    companion object Factory {
        fun dollar(amount: Int): Money = Money(amount, "USD")
        fun franc(amount: Int): Money = Money(amount, "CHF")
    }
}