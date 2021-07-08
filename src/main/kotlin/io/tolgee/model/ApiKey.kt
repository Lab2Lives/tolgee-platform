package io.tolgee.model

import io.tolgee.model.enums.ApiScope
import org.hibernate.envers.Audited
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Suppress("JoinDeclarationAndAssignment")
@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["key"], name = "api_key_unique")])
@Audited
class ApiKey(
        @field:NotEmpty
        @field:NotNull
        var key: String = "",

        @NotNull
        @NotEmpty
        @Enumerated(EnumType.ORDINAL)
        @field:ElementCollection(targetClass = ApiScope::class, fetch = FetchType.EAGER)
        var scopesEnum: MutableSet<ApiScope>
) : StandardAuditModel() {

    @ManyToOne
    @NotNull
    lateinit var userAccount: UserAccount

    @ManyToOne
    @NotNull
    lateinit var project: Project

    constructor(
            key: String, scopesEnum: Set<ApiScope>, userAccount: UserAccount, project: Project
    ) : this(key, scopesEnum.toMutableSet()) {
        this.userAccount = userAccount
        this.project = project
    }
}
