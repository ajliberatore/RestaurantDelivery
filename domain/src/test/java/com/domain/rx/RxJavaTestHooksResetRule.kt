package com.domain.rx

import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Test Rule that ensures unit tests are ran on the [Schedulers.trampoline] ())}
 */
class RxJavaTestHooksResetRule : TestRule {
    private val computationScheduler: Scheduler?
    private val ioScheduler: Scheduler?
    private val newThreadScheduler: Scheduler?

    constructor() {
        computationScheduler = Schedulers.trampoline()
        ioScheduler = Schedulers.trampoline()
        newThreadScheduler = Schedulers.trampoline()
    }

    private constructor(builder: Builder) {
        computationScheduler = builder.computationScheduler
        ioScheduler = builder.ioScheduler
        newThreadScheduler = builder.newThreadScheduler
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { scheduler -> ioScheduler }
                RxJavaPlugins.setComputationSchedulerHandler { scheduler -> computationScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { scheduler -> newThreadScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                }
            }
        }
    }

    class Builder private constructor() {
        var computationScheduler: Scheduler? = null
        var ioScheduler: Scheduler? = null
        var newThreadScheduler: Scheduler? = null

        fun withComputationScheduler(`val`: Scheduler): Builder {
            computationScheduler = `val`
            return this
        }

        fun withIoScheduler(`val`: Scheduler): Builder {
            ioScheduler = `val`
            return this
        }

        fun withNewThreadScheduler(`val`: Scheduler): Builder {
            newThreadScheduler = `val`
            return this
        }

        fun build(): RxJavaTestHooksResetRule {
            if (computationScheduler == null) {
                computationScheduler = Schedulers.trampoline()
            }
            if (ioScheduler == null) {
                ioScheduler = Schedulers.trampoline()
            }
            if (newThreadScheduler == null) {
                newThreadScheduler = Schedulers.trampoline()
            }

            return RxJavaTestHooksResetRule(this)
        }

        companion object {

            fun newBuilder(): Builder {
                return Builder()
            }

            fun newBuilder(copy: RxJavaTestHooksResetRule): Builder {
                val builder = Builder()

                builder.computationScheduler = copy.computationScheduler
                builder.ioScheduler = copy.ioScheduler
                builder.newThreadScheduler = copy.newThreadScheduler
                return builder
            }
        }
    }
}