package evans.dale.job_assessment.service

data class Room (
    val key: String,
    val name: String,
    val thumbnailUrl: String,
    val capacity: Int)

data class RoomDetail(
    val key: String,
    val name: String,
    val heroImageUrl: String,
    val capacity: Int,
    val location: Location,
    val features: List<Feature>,
    val services: List<Service>,
    val facilities: List<Facility>,
    val equipment: List<Equipment> )

data class Location(
    val region: KeyNamePair,
    val site: KeyNamePair,
    val building: KeyNamePair,
    val floor: KeyNamePair )

data class KeyNamePair(
    val key: String,
    val name: String )

data class Feature(
    val key: String,
    val name: String )

data class Service(
    val key: String,
    val name: String,
    val iconUrl: String )

data class Facility(
    val key: String,
    val name: String,
    val iconUrl: String )

data class Equipment(
    val key: String,
    val name: String,
    val iconUrl: String )


