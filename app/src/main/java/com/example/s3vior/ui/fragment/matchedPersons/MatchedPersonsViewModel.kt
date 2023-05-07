package com.example.s3vior.ui.fragment.matchedPersons

import androidx.lifecycle.ViewModel
import com.example.s3vior.domain.model.MafqoudModel
import com.example.s3vior.domain.model.State
import com.example.s3vior.ui.fragment.matchedPersons.model.FoundPerson
import com.example.s3vior.ui.fragment.matchedPersons.model.Location
import com.example.s3vior.ui.fragment.matchedPersons.model.MatchedPersonsResponseModelItem
import com.example.s3vior.ui.fragment.matchedPersons.model.MissedPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MatchedPersonsViewModel @Inject constructor() : ViewModel() {

    private val _matchedPersonsStateFlow =
        MutableStateFlow<State<List<MatchedPersonsResponseModelItem>>>(State.Loading)
    val matchedPersonsStateFlow: StateFlow<State<List<MatchedPersonsResponseModelItem>>> =
        _matchedPersonsStateFlow

    init {
        getMatchedPersons()
    }

    private fun getMatchedPersons() {
        _matchedPersonsStateFlow.value = State.Success(
            listOf(
                MatchedPersonsResponseModelItem(
                    FoundPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.pexels.com/photos/12502083/pexels-photo-12502083.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                        Location(30.4766203, 31.1870528),
                        "Ahmed",
                        "founded"
                    ), MissedPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.pexels.com/photos/10004366/pexels-photo-10004366.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                         Location(30.4766203, 31.1870528),
                        "Eslam",
                        "founded"
                    )
                ),    MatchedPersonsResponseModelItem(
                    FoundPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.pexels.com/photos/6579622/pexels-photo-6579622.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                        Location(30.4766203, 31.1870528),
                        "Mohamed",
                        "founded"
                    ), MissedPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.unsplash.com/photo-1533467795239-6ddbb7b81a58?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwzMzgzfHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNzM2ODE4&ixlib=rb-1.2.1&q=80&w=400",
                        Location(30.4766203, 31.1870528),
                        "Omer",
                        "founded"
                    )
                ),    MatchedPersonsResponseModelItem(
                    FoundPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://i.redd.it/vr2o7iiob5k91.jpg",
                        Location(30.4766203, 31.1870528),
                        "Khaled",
                        "founded"
                    ), MissedPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.unsplash.com/photo-1609789887552-fa440754d322?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw3ODF8fE1vYmlsZSUyMFdhbGxwYXBlcnN8ZW58MHwxfHx8MTY2MTYzMTcyMw&ixlib=rb-1.2.1&q=80&w=400",
                        Location(30.4766203, 31.1870528),
                        "Salem",
                        "founded"
                    )
                ),    MatchedPersonsResponseModelItem(
                    FoundPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.unsplash.com/photo-1649967725384-21b6ce653283?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw3Njh8fE1vYmlsZSUyMFdhbGxwYXBlcnN8ZW58MHwxfHx8MTY2MTYzMTcyMw&ixlib=rb-1.2.1&q=80&w=400",
                        Location(30.4766203, 31.1870528),
                        "Mostafa",
                        "founded"
                    ), MissedPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.unsplash.com/photo-1625222788558-6de8ae17eb0e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw3NjJ8fE1vYmlsZSUyMFdhbGxwYXBlcnN8ZW58MHwxfHx8MTY2MTYzMTcyMw&ixlib=rb-1.2.1&q=80&w=400",
                        Location(30.4766203, 31.1870528),
                        "Nour",
                        "founded"
                    )
                ),    MatchedPersonsResponseModelItem(
                    FoundPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.unsplash.com/photo-1625587774598-bbff8b35e2c3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw0NzY4fHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNjI5MDIx&ixlib=rb-1.2.1&q=80&w=1080",
                        Location(30.4766203, 31.1870528),
                        "Khater",
                        "founded"
                    ), MissedPerson(
                        "10",
                        "12/10",
                        "no desc",
                        "male",
                        1,
                        "https://images.unsplash.com/photo-1661355103273-e3fc8ad60c68?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw0NzQ0fHx2ZXJ0aWNhbHxlbnwwfDF8fHwxNjYxNjI5MDIx&ixlib=rb-1.2.1&q=80&w=400",
                        Location(30.4766203, 31.1870528),
                        "Mohamed",
                        "founded"
                    )
                )
            )
        )
    }

}