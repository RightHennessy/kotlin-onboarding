package onboarding

fun solution7(
    user: String,
    friends: List<List<String>>,
    visitors: List<String>
): List<String> {
    var friendList: List<String>
    var non_friendList: Map<String, Int>
    var result = mutableListOf<String>()
    var i:Int = 0

    friendList = checkFriends(user, friends)
    non_friendList = checkNonFriends(user, friends, friendList, visitors)

    var sortedMap = non_friendList.toList().sortedByDescending { it.second }

    while (i < 5 && i < sortedMap.size){
        if (sortedMap[i].second == 0)
            break
        result.add(sortedMap[i].first)
        i++
    }
    return result
}

fun checkFriends(user: String, friends: List<List<String>>) : List<String> {
    var list = mutableListOf<String>()

    for (friend in friends){
        if (friend[0] == user && !(friend[1] in list))
            list.add(friend[1])
        if (friend[1] == user && !(friend[0] in list))
            list.add(friend[0])
    }
    return list
}

fun checkNonFriends(user: String,
                    friends: List<List<String>>,
                    friendList: List<String>,
                    visitors: List<String>
): Map<String, Int> {
    var list = mutableMapOf<String, Int>()

    for (friend in friends) {
        if (friend[0] == user || friend[1] == user)
            continue
        if (friend[0] in friendList && friend[1] in friendList)
            continue
        if (friend[0] in friendList) {
            if (list.containsKey(friend[1]))
                list.put(friend[1], list.get(friend[1])!! + 10)
            else
                list.put(friend[1], 10)
        }
        if (friend[1] in friendList) {
            if (list.containsKey(friend[0]))
                list.put(friend[0], list.get(friend[0])!! + 10)
            else
                list.put(friend[0], 10)
        }
    }

    for (visitor in visitors) {
        if (visitor in friendList)
            continue
        if (list.containsKey(visitor))
            list.put(visitor, list.get(visitor)!! + 1)
        else
            list.put(visitor, 1)
    }
    return list
}


