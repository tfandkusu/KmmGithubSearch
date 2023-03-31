package com.tfandkusu.kgs.data.remote

import com.tfandkusu.kgs.error.NetworkException
import com.tfandkusu.kgs.error.ServerException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GithubRemoteDataSourceTest {

    private lateinit var remoteDataSource: GithubRemoteDataSource

    @BeforeTest
    fun setUp() {
        remoteDataSource = GithubRemoteDataSourceImpl(
            getMyHttpClient(),
        )
    }

    @Test
    fun search() = runBlocking {
        val repos = remoteDataSource.search("DroidKaigi")
        val index = repos.indexOfFirst { it.fullName == "DroidKaigi/conference-app-2022" }
        index shouldBeGreaterThanOrEqual 0
        val repo = repos[index]
        // 変化が想定される箇所は緩めの判定文にする
        repo.id shouldBe 517191221
        repo.fullName shouldBe "DroidKaigi/conference-app-2022"
        repo.login shouldBe "DroidKaigi"
        repo.avatarUrl.startsWith("https://avatars.githubusercontent.com/") shouldBe true
        repo.language shouldBe "Kotlin"
        repo.stargazersCount shouldBeGreaterThan 100
        repo.watchersCount shouldBeGreaterThan 100
        repo.forksCount shouldBeGreaterThan 100
        repo.openIssuesCount shouldBeGreaterThanOrEqual 0
        Unit
    }

    @Test
    fun searchJapanese() = runBlocking {
        val repos = remoteDataSource.search("やられる直前15秒")
        repos.size shouldBe 1
        repos[0].fullName shouldBe "tfandkusu/splatoon2_death_docker"
    }

    @Test
    fun networkError(): Unit = runBlocking {
        shouldThrow<NetworkException> {
            remoteDataSource.checkNetworkError()
        }
    }

    @Test
    fun serverError(): Unit = runBlocking {
        shouldThrow<ServerException> {
            remoteDataSource.checkServerError()
        } shouldBe ServerException(404)
    }

    @Test
    fun jsonDecode() {
        val jsonString = """
        {
          "total_count": 87,
          "incomplete_results": false,
          "items": [
            {
              "id": 517191221,
              "node_id": "R_kgDOHtO2NQ",
              "name": "conference-app-2022",
              "full_name": "DroidKaigi/conference-app-2022",
              "private": false,
              "owner": {
                "login": "DroidKaigi",
                "id": 10727543,
                "node_id": "MDEyOk9yZ2FuaXphdGlvbjEwNzI3NTQz",
                "avatar_url": "https://avatars.githubusercontent.com/u/10727543?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/DroidKaigi",
                "html_url": "https://github.com/DroidKaigi",
                "followers_url": "https://api.github.com/users/DroidKaigi/followers",
                "following_url": "https://api.github.com/users/DroidKaigi/following{/other_user}",
                "gists_url": "https://api.github.com/users/DroidKaigi/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/DroidKaigi/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/DroidKaigi/subscriptions",
                "organizations_url": "https://api.github.com/users/DroidKaigi/orgs",
                "repos_url": "https://api.github.com/users/DroidKaigi/repos",
                "events_url": "https://api.github.com/users/DroidKaigi/events{/privacy}",
                "received_events_url": "https://api.github.com/users/DroidKaigi/received_events",
                "type": "Organization",
                "site_admin": false
              },
              "html_url": "https://github.com/DroidKaigi/conference-app-2022",
              "description": "The Official Conference App for DroidKaigi 2022",
              "fork": false,
              "url": "https://api.github.com/repos/DroidKaigi/conference-app-2022",
              "forks_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/forks",
              "keys_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/keys{/key_id}",
              "collaborators_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/collaborators{/collaborator}",
              "teams_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/teams",
              "hooks_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/hooks",
              "issue_events_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/issues/events{/number}",
              "events_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/events",
              "assignees_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/assignees{/user}",
              "branches_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/branches{/branch}",
              "tags_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/tags",
              "blobs_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/git/blobs{/sha}",
              "git_tags_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/git/tags{/sha}",
              "git_refs_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/git/refs{/sha}",
              "trees_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/git/trees{/sha}",
              "statuses_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/statuses/{sha}",
              "languages_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/languages",
              "stargazers_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/stargazers",
              "contributors_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/contributors",
              "subscribers_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/subscribers",
              "subscription_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/subscription",
              "commits_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/commits{/sha}",
              "git_commits_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/git/commits{/sha}",
              "comments_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/comments{/number}",
              "issue_comment_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/issues/comments{/number}",
              "contents_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/contents/{+path}",
              "compare_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/compare/{base}...{head}",
              "merges_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/merges",
              "archive_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/{archive_format}{/ref}",
              "downloads_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/downloads",
              "issues_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/issues{/number}",
              "pulls_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/pulls{/number}",
              "milestones_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/milestones{/number}",
              "notifications_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/notifications{?since,all,participating}",
              "labels_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/labels{/name}",
              "releases_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/releases{/id}",
              "deployments_url": "https://api.github.com/repos/DroidKaigi/conference-app-2022/deployments",
              "created_at": "2022-07-24T00:15:30Z",
              "updated_at": "2023-03-28T15:54:44Z",
              "pushed_at": "2023-03-29T10:51:56Z",
              "git_url": "git://github.com/DroidKaigi/conference-app-2022.git",
              "ssh_url": "git@github.com:DroidKaigi/conference-app-2022.git",
              "clone_url": "https://github.com/DroidKaigi/conference-app-2022.git",
              "svn_url": "https://github.com/DroidKaigi/conference-app-2022",
              "homepage": "https://droidkaigi.jp/2022/",
              "size": 13424,
              "stargazers_count": 450,
              "watchers_count": 450,
              "language": "Kotlin",
              "has_issues": true,
              "has_projects": true,
              "has_downloads": true,
              "has_wiki": true,
              "has_pages": true,
              "has_discussions": false,
              "forks_count": 193,
              "mirror_url": null,
              "archived": false,
              "disabled": false,
              "open_issues_count": 39,
              "license": {
                "key": "apache-2.0",
                "name": "Apache License 2.0",
                "spdx_id": "Apache-2.0",
                "url": "https://api.github.com/licenses/apache-2.0",
                "node_id": "MDc6TGljZW5zZTI="
              },
              "allow_forking": true,
              "is_template": false,
              "web_commit_signoff_required": false,
              "topics": [
                "android",
                "android-app",
                "droidkaigi",
                "kotlin-multiplatform"
              ],
              "visibility": "public",
              "forks": 193,
              "open_issues": 39,
              "watchers": 450,
              "default_branch": "main",
              "score": 1.0
            }
          ]
        }
        """.trimIndent()
        val response = decodeGithubResponse(jsonString)
        response.items.size shouldBe 1
        response.items[0].fullName shouldBe "DroidKaigi/conference-app-2022"
        response.items[0].language shouldBe "Kotlin"
        response.items[0].owner.login shouldBe "DroidKaigi"
        response.items[0].owner.avatarUrl shouldBe "https://avatars.githubusercontent.com/u/10727543?v=4"
        response.items[0].forksCount shouldBe 193
    }
}
