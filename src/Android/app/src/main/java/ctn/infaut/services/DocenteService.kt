package ctn.infaut.services

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

val supabase = createSupabaseClient(
    supabaseUrl = "https://isdfoxfbdmrfnkymrkqf.supabase.co",
    supabaseKey = "sb_publishable_ECo-YzAtjpdFOsGOaKbH4Q_WX89nd6v",
) {
    install(Postgrest)
}

class DocenteService {

}