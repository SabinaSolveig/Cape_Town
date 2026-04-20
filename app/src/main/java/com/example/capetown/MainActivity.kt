package com.example.capetown

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.capetown.databinding.ActivityMainBinding
import com.example.capetown.viewmodel.SharedViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel: SharedViewModel
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        setupToolbar()
        setupNavigation()
        setupBottomNavigation()
        observeViewModel()

        savedInstanceState?.getInt("selected_bottom_nav")?.let {
            binding.bottomNavigation?.selectedItemId = it
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        drawerToggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_nature,
            R.id.navigation_culture,
            R.id.navigation_wine,
            R.id.navigation_adventure,
            R.id.navigation_entertainment,
            R.id.navigation_about,
            R.id.navigation_settings
        ).setOpenableLayout(binding.drawerLayout).build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        binding.navView.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation?.setupWithNavController(navController)

        binding.bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_nature -> {
                    navigateToCategory("nature", item.itemId)
                    true
                }
                R.id.navigation_culture -> {
                    navigateToCategory("culture", item.itemId)
                    true
                }
                R.id.navigation_wine -> {
                    navigateToCategory("wine", item.itemId)
                    true
                }
                R.id.navigation_adventure -> {
                    navigateToCategory("adventure", item.itemId)
                    true
                }
                R.id.navigation_entertainment -> {
                    navigateToCategory("entertainment", item.itemId)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToCategory(categoryId: String, itemId: Int) {
        viewModel.loadPlaces(categoryId)
        viewModel.setSelectedBottomNavItem(itemId)

        val destinationId = when (categoryId) {
            "nature" -> R.id.navigation_nature
            "culture" -> R.id.navigation_culture
            "wine" -> R.id.navigation_wine
            "adventure" -> R.id.navigation_adventure
            "entertainment" -> R.id.navigation_entertainment
            else -> R.id.navigation_nature
        }

        navController.navigate(destinationId, null, null)
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun observeViewModel() {
        viewModel.toolbarTitle.observe(this) { title ->
            supportActionBar?.title = title
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_nature -> navigateToCategory("nature", R.id.navigation_nature)
            R.id.navigation_culture -> navigateToCategory("culture", R.id.navigation_culture)
            R.id.navigation_wine -> navigateToCategory("wine", R.id.navigation_wine)
            R.id.navigation_adventure -> navigateToCategory("adventure", R.id.navigation_adventure)
            R.id.navigation_entertainment -> navigateToCategory("entertainment", R.id.navigation_entertainment)
            R.id.navigation_about -> {
                navController.navigate(R.id.navigation_about)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.navigation_settings -> {
                navController.navigate(R.id.navigation_settings)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.navigation_home -> {
                navController.navigate(R.id.navigation_home)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.bottomNavigation?.selectedItemId?.let { outState.putInt("selected_bottom_nav", it) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}