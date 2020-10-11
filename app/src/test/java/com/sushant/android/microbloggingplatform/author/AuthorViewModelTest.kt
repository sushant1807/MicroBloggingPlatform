package com.sushant.android.microbloggingplatform.author

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sushant.android.microbloggingplatform.ui.authorList.AuthorViewModel
import com.sushant.android.microbloggingplatform.util.InstantExecutorExtension
import com.sushant.android.microbloggingplatform.util.TestModelsGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class AuthorViewModelTest {

    // Subject under test
    private lateinit var authorViewModel: AuthorViewModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var authorName: String
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        // Create class under test
        // We initialise the repository with no tasks
        authorName = testModelsGenerator.generateAuthorResponse().name
        val newsModelSuccess =  authorViewModel.getLocaleAuthors()
    }

    @Test
    fun handleAuthorList() {

        val isEmptyList =authorName.isNullOrEmpty()
        assert(!isEmptyList)
    }


}