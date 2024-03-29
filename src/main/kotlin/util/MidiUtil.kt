package util

import kotlin.math.pow

object MidiUtil {
    enum class MetaType(val value: Byte) {
        Text(0x01),
        TrackName(0x03),
        Tempo(0x51),
        TimeSignature(0x58),
        EndOfTrack(0x2f);

        val eventHeaderBytes get() = listOf(0xff.toByte(), value)

        companion object {
            fun parse(value: Byte?): MetaType? = values().find { it.value == value }
        }
    }

    fun convertMidiTempoToBpm(midiTempo: Int) =
        ((1000 * 1000 * 60 / midiTempo.toDouble()) * 100).toInt().toDouble() / 100

    fun convertBpmToMidiTempo(bpm: Double) =
        (1000 * 1000 * 60 / bpm).toInt()

    fun parseMidiTimeSignature(data: dynamic): Pair<Int, Int> {
        data as Array<Int>
        val numerator = data[0]
        val denominator = (2f.pow(data[1])).toInt()
        return numerator to denominator
    }

    fun generateMidiTimeSignatureBytes(numerator: Int, denominator: Int): List<Byte> {
        return listOf(
            numerator.toByte(),
            kotlin.math.log2(denominator.toDouble()).toInt().toByte(),
            0x18,
            0x08,
        )
    }
}
