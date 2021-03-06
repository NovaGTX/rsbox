package io.rsbox.engine.model.world

import io.rsbox.engine.Engine
import io.rsbox.engine.cache.def.CacheData
import io.rsbox.engine.model.LivingEntityList
import io.rsbox.engine.model.entity.Player
import io.rsbox.engine.service.ServiceManager
import io.rsbox.engine.service.impl.XteaKeyService
import io.rsbox.util.HuffmanCodec
import net.runelite.cache.IndexType
import java.security.SecureRandom
import java.util.*

/**
 * @author Kyle Escobar
 */

class World(val engine: Engine) : io.rsbox.api.world.World {

    val random: Random = SecureRandom()

    val players = LivingEntityList(arrayOfNulls<Player>(2000))

    lateinit var xteaKeyService: XteaKeyService

    val huffman by lazy {
        val binary = Engine.cacheStore.getIndex(IndexType.BINARY)!!
        val archive = binary.findArchiveByName("huffman")!!
        val file = archive.getFiles(Engine.cacheStore.storage.loadArchive(archive)!!).files[0]
        HuffmanCodec(file.contents)
    }

    val cache: CacheData get() = engine._cache

    override fun preLoad() {

    }

    override fun load() {

    }

    override fun postLoad() {
        xteaKeyService = ServiceManager[XteaKeyService::class.java]!!
    }

    internal fun registerPlayer(player: Player): Boolean {
        val registered = players.add(player)
        if(registered) {
            player.lastIndex = player.index
            return true
        }
        return false
    }

    internal fun unregisterPlayer(player: Player): Boolean {
        return players.remove(player)
    }
}