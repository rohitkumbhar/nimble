package grails.plugin.nimble.core

import grails.util.Holders as ConfigurationHolder

/**
 * Represents a grouping of users in a Nimble based appication
 *
 * @author Bradley Beddoes
 */
class Group implements Serializable{

	String name
	String description
	String realm

	boolean external = false
	boolean protect = false

	Date dateCreated
	Date lastUpdated

	static hasMany = [
		roles: Role,
		users: UserBase,
		permissions: Permission
	]

	static mapping = {
		sort "name"
		cache usage: 'read-write', include: 'all'
		table ConfigurationHolder.config.nimble.tablenames.group

		//users cache: true
		roles cache: true
		permissions cache: true
	}

	static constraints = {
		name(blank: false, unique: true, minSize:4, maxSize: 255)
		description(nullable: true, blank: false)
		realm(nullable: true, blank: false)

		dateCreated(nullable: true) // must be true to enable grails
		lastUpdated(nullable: true) // auto-inject to be useful which occurs post validation

		permissions(nullable:true)
	}
}
