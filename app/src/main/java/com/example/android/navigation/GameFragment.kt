/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    //correct answer
    private val correct = (Math.random()*50).toInt()+1

    private var time = 0

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)



        binding.submitButton.setOnClickListener {
            if(time>9) {
                view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(correct))
            }
            else{
                time = time+1
                if((binding.guess.text.toString()?.toInt()) == correct){
                    view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(time))
                }
                else if(time==10){
                    view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(correct))
                }
                else{
                    compare(it)
                }
            }
        }
        return binding.root
    }

    private fun compare(view: View) {
        binding.apply {
            val guess_value = guess.text.toString()?.toInt()
            if(guess_value>correct){
                scope.setText("Should be less than " + guess_value)
            }
            else if(guess_value<correct){
                scope.setText("Should be greater than " + guess_value)
            }
        }
    }

}
